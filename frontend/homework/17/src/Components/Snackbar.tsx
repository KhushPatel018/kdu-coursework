import { Alert, Fade, Snackbar } from "@mui/material";
import { TransitionProps } from "@mui/material/transitions";
import React, { useEffect, useRef, useState } from "react";

export function SnackbarComponent({
  type,
  content,
}: Readonly<{ type: "success" | "error"; content: string }>) {
  const [state, setState] = useState<{
    open: boolean;
    Transition: React.ComponentType<
      TransitionProps & {
        children: React.ReactElement<any, any>;
      }
    >;
  }>({
    open: true,
    Transition: Fade,
  });
  const snakref = useRef(false);
  const handleClose = () => {
    setState({
      ...state,
      open: false,
    });
  };

  // handle snackbar appear on the main page load only
  useEffect(() => {
    if (!snakref.current) {
      snakref.current = true;
    }
  }, []);
  return (
    <div>
      <Snackbar
        open={state.open}
        TransitionComponent={state.Transition}
        key={state.Transition.name}
        autoHideDuration={1800}
        onClose={handleClose}
      >
        <Alert
          onClose={handleClose}
          severity={type}
          variant="filled"
          sx={{ width: "100%" }}
        >
          {content}
        </Alert>
      </Snackbar>
    </div>
  );
}
