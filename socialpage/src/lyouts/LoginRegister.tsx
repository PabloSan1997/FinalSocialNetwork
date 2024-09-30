

import { Register } from "../components/Register";
import Login from "../components/Login";
import React from "react";
import { useAppSelector } from "../store/hooks";
import { Navigate } from "react-router-dom";

export  function LoginRegister() {
  const token = useAppSelector(state => state.authReducer.token);
  React.useEffect(()=>{
    document.title = 'Login';
  },[])
  if(token)
    return(<Navigate to={'/home'}/>);
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
