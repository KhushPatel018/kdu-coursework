import React, { useState } from "react";
import { useAppSelector } from "../Redux/store";
import { Room, RoomType } from "../types";
import styled from "styled-components";
import dayjs, { Dayjs } from "dayjs";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DateField } from "@mui/x-date-pickers/DateField";
import { Container } from "./Home.styled";



export function Home() {
  const rooms = useAppSelector((state) => state.Rooms.rooms);
  const loading = useAppSelector((state) => state.Rooms.loading);
  const error = useAppSelector((state) => state.Rooms.error);

  const [active, setActive] = useState<string>("None");
  const [startDate, setStartDate] = useState<any>();
  const [endDate, setEndDate] = useState<any>();
  const [selectedAddOns, setSelectedAddOns] = useState<string[]>([]);
  const [cost, setCost] = useState<number>(0);
  const findByName = (active: string) => {
    return rooms.find((room) => room.name === active);
  };

  const handleRoomTypeSelection = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    const val = e.target.value;
    setActive(val);
    // reset addons
    setSelectedAddOns([]);
  };

  const handleAddOnSelect = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    const val = e.target.value;
    if (selectedAddOns.includes(val)) {
      setSelectedAddOns((prev) => prev.filter((add) => add !== val));
    } else {
      setSelectedAddOns((prev) => [...prev, val]);
    }
  };

  const handleSubmit = () => {
    if (!endDate) {
      alert("end date is required field");
      return;
    }
    if (!startDate) {
      alert("start date is required field");
      return;
    }
    let Difference_In_Time = endDate.$d.getTime() - startDate.$d.getTime();
    if (Difference_In_Time < 0) {
      alert("please add valid start and end date");
      return;
    }
    let Difference_In_Days = Math.round(
      Difference_In_Time / (1000 * 3600 * 24)
    );

    const daydiff = Difference_In_Days;
    let addOnTotal = 0;
    const roomTaken = findByName(active);
    const roomPrice = parseInt(roomTaken?.costPerNight!);
    const currency = roomTaken?.currency;
    const addOnArray = roomTaken?.addOns;
    selectedAddOns.forEach((addOn) => {
      const addOnObject = addOnArray?.find((addon) => addon.name === addOn);
      if (addOnObject) {
        addOnTotal += parseInt(addOnObject.cost);
      }
    });

    const cost = (roomPrice + addOnTotal) * daydiff;
    const totalCost = cost + cost * 0.18;
    setCost(totalCost);
    console.log("Cost : ", totalCost);
    console.log("days stayed : ", daydiff);
    console.log("taken room : ", JSON.stringify(roomTaken, null, 2));
    console.log("add ons taken : ", addOnArray);
  };
  return (
    <Container $active={active}>
      <div className="title">Hotel Booking</div>
      <div className="label roomType">Select Room Type</div>
      <div className="roomTypeButtons">
        {rooms.map((room: RoomType) => (
          <button
            className="radioButton"
            key={room.id}
            value={room.name}
            onClick={handleRoomTypeSelection}
            id={room.name}
            style={
              active === room.name
                ? { border: "1px solid orange", color: "orange" }
                : { border: "1px solid black" }
            }
          >
            {room.name}
          </button>
        ))}
      </div>
      <div className="label">Select Date</div>

      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <DemoContainer components={["DateField", "DateField"]}>
          <div className="dates">
            <DateField
              label="start date"
              value={startDate}
              onChange={(newValue) => setStartDate(newValue!)}
              required
              className="date"
            />
            <DateField
              label="end date"
              value={endDate}
              required
              onChange={(newValue) => setEndDate(newValue!)}
              className="date"
            />
          </div>
        </DemoContainer>
      </LocalizationProvider>
      <div className="label add-on-head">
        Select additional add ons / preferences
      </div>
      <div className="add-ons">
        {active !== "None" &&
          findByName(active)?.addOns.map((add) => (
            <button
              className="radioButton"
              key={add.name}
              value={add.name}
              onClick={handleAddOnSelect}
              style={
                selectedAddOns.includes(add.name)
                  ? { border: "1px solid orange", color: "orange" }
                  : { border: "1px solid black" }
              }
            >
              {add.name}
            </button>
          ))}
      </div>
      <div className="cost">Cost + 18% GST : {cost}</div>
      <button className="submit" onClick={handleSubmit} style={active === 'None'  ?  {opacity : "0.5"} : {opacity : "1",cursor : "pointer"} }>
        Submit
      </button>
    </Container>
  );
}
