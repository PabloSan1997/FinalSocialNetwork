

import React from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { authActions } from "../slices/authSlice";
import { authExtreReducer } from "../slices/extraReducer/authExtreReducer";

export default function Login() {
    const dispatch = useAppDispatch();
    const state = useAppSelector(state => state.authReducer);
    const submit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const data = new FormData(e.target as HTMLFormElement);
        const loginForm: LoginDto = {
            username: data.get('username') as string,
            password: data.get('password') as string
        }
        if (!loginForm.username.trim()) {
            dispatch(authActions.writeMessage({ message: 'Escriba su nombre de usuario' }));
        } else if (!loginForm.password.trim()) {
            dispatch(authActions.writeMessage({ message: 'Escriba su contraseña' }));
        } else {
            dispatch(authExtreReducer.login({ login: loginForm }));
        }

    }
    return (
        <form className="login" onSubmit={submit}>
            <label htmlFor="login_username">Username</label>
            <input type="text" name="username" id="login_username" />
            <label htmlFor="login_password">Password</label>
            <input type="password" name="password" id="login_password" />
            <button className="" type="submit">Entrar</button>
            {state.message.trim() ? <p>{state.message}</p> : null}
        </form>
    );
}
