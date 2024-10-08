import { Register } from "../components/Register";
import Login from "../components/Login";
import React from "react";
import { useAppSelector } from "../store/hooks";
import { Navigate } from "react-router-dom";
import '../styles/login_register.scss';

export function LoginRegister() {
  const token = useAppSelector(state => state.authReducer.token);
  React.useEffect(() => {
    document.title = 'Login';
  }, [])
  if (token)
    return (<Navigate to={'/home'} />);
  return (
    <div className="area_forms">
      <div className="area_form register_area">
        <h2>Register</h2>
        <Register />
      </div>
      <div className="area_form login_area">
        <h2>Login</h2>
        <Login />
      </div>
    </div>
  );
}
