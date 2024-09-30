

import { Register } from "../components/Register";
import Login from "../components/Login";
import React from "react";

export  function LoginRegister() {
  React.useEffect(()=>{
    document.title = 'Login';
  },[])
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
