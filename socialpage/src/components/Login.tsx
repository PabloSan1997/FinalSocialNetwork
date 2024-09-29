

import React from "react";

export default function Login() {
    const submit = (e:React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const data = new FormData(e.target as HTMLFormElement);
        const loginForm:LoginDto = {
            username:data.get('username') as string,
            password:data.get('password') as string
        }
        console.log(loginForm);

    }
    return (
        <form className="login" onSubmit={submit}>
            <label htmlFor="login_username">Username</label>
            <input type="text" name="username" id="login_username" />
            <label htmlFor="login_password">Password</label>
            <input type="password" name="password" id="login_password" />
            <button className="" type="submit">Entrar</button>
        </form>
    );
}
