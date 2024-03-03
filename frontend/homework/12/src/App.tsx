import React, { useRef } from "react";
import Timer from "./Components/Timer";
import { Form } from "./Components/Form";
import { ScrollToTopButton } from "./Components/ScrollToTopButton";



export function App() {
  const mutableValueRef = useRef<number[]>([]);
  const scrollToTopRef = useRef<HTMLDivElement>(null);
  // Modify mutableValue
  const modifyMutableValue = (newValue: number) => {
    mutableValueRef.current.push(newValue);
    console.log(mutableValueRef.current); // Logs the updated mutable value
  };
  return (
    <div>
      <div className="header" ref={scrollToTopRef}>
        This is header Scroll will come here
      </div>

      {/* Focus */}
      <Form />

      {/* Timer */}
      <Timer />

      {/* Scroll */}
      <div style={{ height: "2000px" }}>Scroll down...</div>

      <div>
        <ScrollToTopButton scrollRef={scrollToTopRef} />
      </div>

      {/* it won't cause rerender so means we can maintain mutable variable across the renders */}
      <button onClick={() => modifyMutableValue(1)}>Modify Value</button>
    </div>
  );
}
