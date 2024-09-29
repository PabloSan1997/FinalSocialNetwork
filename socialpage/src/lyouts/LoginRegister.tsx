

import React from "react";
import { Register } from "../components/Register";
import Login from "../components/Login";

export  function LoginRegister() {
  return (
    <div className="area_forms">
        <div className="area_form">
            <h2>Login</h2>
            <Login/>
        </div>
        <div className="area_form">
            <h2>Register</h2>
            <Register/>
        </div>
    </div>
  );
}
