import React, { useRef, useEffect } from "react";

export function Form() {
  const firstInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    // Focus on the first input field when the component mounts
    if (firstInputRef.current) {
      firstInputRef.current.focus();
    }
  }, []);

  return (
    <form>
      <label>
        First Name: <input type="text" ref={firstInputRef} />
      </label>
      <br />
      <label>
        Last Name: <input type="text" />
      </label>
      <br />
      <button type="submit">Submit</button>
    </form>
  );
}
