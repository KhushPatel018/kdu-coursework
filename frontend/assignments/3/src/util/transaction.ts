import {
  ApiTransactionData,
  TransactionsByDate,
} from "../Types/TransactionType";

export function extractDateAndTime(timestamp: string): { date: string; time: string } {
  const date = timestamp.split("T")[0];
  const Time = timestamp.split("T")[1].split(".")[0];
  return { date, time: Time };
}

export function parseTime(time: string): {
  hours: number;
  minutes: number;
  seconds: number;
} {
  const [hours, minutes, seconds] = time
    .split(":")
    .map((value) => parseInt(value));
  return { hours, minutes, seconds };
}

function groupTransactionsByDate(
  apiData: ApiTransactionData[]
): Map<string, TransactionsByDate> {
  const transactionsByDate = new Map<string, TransactionsByDate>();

  apiData.forEach((transaction) => {
    const { date, time } = extractDateAndTime(transaction.timestamp);

    if (!transactionsByDate.has(date)) {
      transactionsByDate.set(date, { date, transactions: [], length: 0 });
    }

    transactionsByDate.get(date)!.transactions.push({
      stock_name: transaction.stock_name,
      stock_symbol: transaction.stock_symbol,
      transaction_price: transaction.transaction_price,
      status: transaction.status,
      time: parseTime(time),
      isVisible: true,
      opacify : false
    });

    transactionsByDate.get(date)!.length++;
  });

  return transactionsByDate;
}

function sortTransactionsByDate(
  transactionsByDate: Map<string, TransactionsByDate>
): TransactionsByDate[] {
  const parsedData = Array.from(transactionsByDate.values()).sort((a, b) => {
    const dateA = new Date(a.date);
    const dateB = new Date(b.date);
    return dateB.getTime() - dateA.getTime();
  });

  return parsedData;
}

function sortTransactionsByTime(parsedData: TransactionsByDate[]): void {
  parsedData.forEach((entry) => {
    entry.transactions.sort((a, b) => {
      const timeA = new Date(
        0,
        0,
        0,
        a.time.hours,
        a.time.minutes,
        a.time.seconds
      );
      const timeB = new Date(
        0,
        0,
        0,
        b.time.hours,
        b.time.minutes,
        b.time.seconds
      );
      return timeB.getTime() - timeA.getTime();
    });
  });
}

export function processAPIData(apiData: ApiTransactionData[]): TransactionsByDate[] {
  const transactionsByDate = groupTransactionsByDate(apiData);
  const parsedData = sortTransactionsByDate(transactionsByDate);
  sortTransactionsByTime(parsedData);
  return parsedData;
}



export function formatDateToISTString(date : Date) {
  // Convert the date to Indian Standard Time (IST)
  let ISTOffset = 5.5 * 60 * 60 * 1000; // IST is UTC+5:30
  let ISTTimestamp = date.getTime() + ISTOffset;
  let ISTDate = new Date(ISTTimestamp);

  // Extract date components
  let year = ISTDate.getFullYear();
  let month = String(ISTDate.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed
  let day = String(ISTDate.getDate()).padStart(2, '0');

  // Extract time components
  let hours = String(ISTDate.getHours()).padStart(2, '0');
  let minutes = String(ISTDate.getMinutes()).padStart(2, '0');
  let seconds = String(ISTDate.getSeconds()).padStart(2, '0');
  let milliseconds = String(ISTDate.getMilliseconds()).padStart(3, '0');

  // Construct the formatted string
  let formattedDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.${milliseconds}Z`;

  return formattedDate;
}

