import { useParams } from "react-router";
import { Header } from "../Header/Header";
import { useAppDispatch, useAppSelector } from "../../redux/store";
import {
  ArrowIcon,
  Notification,
  Wrapper,
} from "./StockPage.styled";
import { useEffect, useState } from "react";
import { socket } from "../../socket";
import { Link } from "react-router-dom";
import {
  setBalance,
  setHistory,
  setStockBalance,
  setUsername,
} from "../../redux/UserSlice/userSlice";
import HistoryCard from "./HistoryCard/HistoryCard";
import { notificationType } from "../../Types/TransactionType";
import { addTransaction } from "../../redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";
import {
  BarChart,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  Bar,
} from "recharts";

export default function StockPage() {
  const [isActive, setIsActive] = useState(false);
  const stocks = useAppSelector((state) => state.Stocks.stocks);
  const [prices, setPrices] = useState<{ price: number; hike: number }[]>([]);
  const graphPrices = prices.map((pr) => {
    let fill;
    if (pr.hike >= 0) {
      fill = "#b2f2bb";
    } else {
      fill = "#ffc9c9";
    }
    return { price: pr.price, fill: fill };
  });
  const { stockName } = useParams();
  const curruntStock = stocks.find((stk) => stk.stock_name === stockName);
  const [price, setPrice] = useState<{ price: number; hike: number }>({
    price: curruntStock?.base_price!,
    hike: 0.0,
  });
  const [notifications, setNotifactions] = useState<string[]>([]);

  useEffect(() => {
    // no-op if the socket is already connected
    socket.connect();
    socket.emit("join-room", stockName);
    const interval = setInterval(() => {
      socket.emit("price", (curruntStock?.base_price ?? 100) % 500);
    }, 2000);
    return () => {
      socket.disconnect();
      clearInterval(interval);
    };
  }, []);

  useEffect(() => {
    setPrices([]);
    setIsActive(false);
  }, [curruntStock]);

  const history = useAppSelector((state) => state.User.history);
  const balance = useAppSelector((state) => state.User.balance);
  const user = useAppSelector((state) => state.User.user);
  const [quantity, setQuantity] = useState<number | null>(null);
  const dispatcher = useAppDispatch();

  useEffect(() => {
    function onPriceEvent(value: { price: number; hike: number }) {
      setPrice(value);
      setPrices((prev) => [...prev, value]);
    }
    function onJoin(value: string) {
      dispatcher(setUsername(value));
    }
    function onNewTransaction(value: notificationType) {
      const notify = `${value.time};${value.username}  ${
        value.type === "buy" ? "Bought" : "Sold"
      }  ${value.qty}  ${value.stock_name}`;
      setNotifactions((prev) => [...prev, notify]);
    }

    function onInitialLoad(value: notificationType[]) {
      value.forEach(onNewTransaction);
    }
    socket.on("join", onJoin);
    socket.on("initial-transactions", onInitialLoad);
    socket.on("priceResponse", onPriceEvent);
    socket.on("new-transaction", onNewTransaction);
    return () => {
      socket.off("priceResponse", onPriceEvent);
      socket.off("join", onJoin);
      socket.off("initial-transactions", onInitialLoad);
      socket.off("new-transaction", onNewTransaction);
    };
  }, [socket, curruntStock, dispatcher]);

  const stockBalance = useAppSelector((state) => state.User.StockBalance);

  const handleTransaction = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>,
    type: string
  ) => {
    const stk = stockBalance.find((sb) => sb.stockname === stockName);
    if (type === "buy") {
      if (quantity! * price.price > balance) {
        alert("not enough balance to buy");
        dispatcher(
          addTransaction({
            stock_name: curruntStock?.stock_name!,
            stock_symbol: curruntStock?.stock_symbol!,
            transaction_price: parseFloat(
              (quantity! * price.price).toPrecision(6)
            ),
            status: "Failed",
            timestamp: new Date().toISOString(),
          })
        );
        return;
      } else {
        dispatcher(setBalance(balance - quantity! * price.price));
        dispatcher(
          setHistory([
            ...history,
            {
              type: "buy",
              qty: quantity!,
              time: new Date().toLocaleDateString(),
            },
          ])
        );
        const newStockBalance = (stk?.qty ?? 0) + quantity!;
        dispatcher(
          setStockBalance({
            stockname: stockName!,
            qty: newStockBalance,
          })
        );
        socket.emit("new-transaction", {
          stock_name: stockName,
          time: `${new Date().getHours()}:${new Date().getMinutes()}`,
          qty: quantity,
          type: type,
          username: user,
        });
      }
    } else {
      if ((stk?.qty ?? 0) < quantity!) {
        alert("not enough stocks to sell");
        dispatcher(
          addTransaction({
            stock_name: curruntStock?.stock_name!,
            stock_symbol: curruntStock?.stock_symbol!,
            transaction_price: parseFloat(
              (quantity! * price.price).toPrecision(6)
            ),
            status: "Failed",
            timestamp: new Date().toISOString(),
          })
        );
        return;
      } else {
        dispatcher(setBalance(balance + quantity! * price.price));
        dispatcher(
          setHistory([
            ...history,
            {
              type: "sell",
              qty: quantity!,
              time: new Date().toLocaleDateString(),
            },
          ])
        );
        const newStockBalance = (stk?.qty ?? 0) - quantity!;
        console.log("stock balance after sell", newStockBalance);
        dispatcher(
          setStockBalance({ stockname: stockName!, qty: newStockBalance })
        );
        socket.emit("new-transaction", {
          stock_name: stockName,
          time: `${new Date().getHours()}:${new Date().getMinutes()}`,
          qty: quantity,
          type: type,
          username: user,
        });
      }
    }

    dispatcher(
      addTransaction({
        stock_name: curruntStock?.stock_name!,
        stock_symbol: curruntStock?.stock_symbol!,
        transaction_price: parseFloat((quantity! * price.price).toPrecision(6)),
        status: "Passed",
        timestamp: new Date().toISOString(),
      })
    );
    setQuantity(0);
  };

  return (
    <div>
      <Header />
      <Wrapper>
        <div className="stock-container">
          <div className="display">
            <div className="company">
              <div
                className="companies"
                onClick={() => setIsActive(true)}
                id=""
              >
                <div className="company-logo">
                  {curruntStock?.stock_name?.substring(0, 3).toUpperCase()}
                </div>
                <div className="name">{curruntStock?.stock_name}</div>
              </div>
              {isActive && (
                <div className="dropdown-content">
                  {stocks.map((stk) => (
                    <Link to={`/${stk.stock_name}`} key={stk.stock_name}>
                      <div className="dropdown-item" id={stk.stock_name}>
                        <div className="company-logo">
                          {stk.stock_name?.substring(0, 3).toUpperCase()}
                        </div>
                        <div className="name">{stk.stock_name}</div>
                      </div>
                    </Link>
                  ))}
                </div>
              )}
            </div>
            <div className="price">
              <div className="lable">Price</div>
              <div
                className="value"
                style={
                  price.hike >= 0 ? { color: "#2f9e44" } : { color: "#e03131" }
                }
              >
                {price.price}{" "}
                <ArrowIcon isUp={price.hike}>
                  {price.hike > 0 ? "↑" : "↓"}
                </ArrowIcon>
              </div>
              <div className="hike">{price.hike.toPrecision(4)}%</div>
            </div>
            <input
              type="number"
              id="quntity"
              value={quantity!}
              onChange={(e) => setQuantity(parseInt(e.target.value))}
              placeholder="enter QTY"
            />
            <button id="buy" onClick={(e) => handleTransaction(e, "buy")}>
              BUY
            </button>
            <button id="sell" onClick={(e) => handleTransaction(e, "sell")}>
              SELL
            </button>
          </div>
          <BarChart
            width={1050}
            height={600}
            data={graphPrices}
            barGap={0}
            barCategoryGap={0}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis />
            <YAxis />
            <Tooltip />
            <Legend />
            <Bar dataKey="price" barSize={20} />
          </BarChart>
        </div>
        <div className="right-side-wrapper">
          <div className="history">
            <div className="heading">History</div>
            <div className="transaction-box">
              {history.map((his) => (
                <HistoryCard key={his.time} {...his} />
              ))}
            </div>
          </div>
          <div className="notifications">
            {notifications.map((noti) => (
              <Notification key={noti}>
                <p className="notification-text">{noti.split(";")[1]}</p>
                <p className="noti-time">{noti.split(";")[0]}</p>
              </Notification>
            ))}
          </div>
        </div>
      </Wrapper>
    </div>
  );
}
