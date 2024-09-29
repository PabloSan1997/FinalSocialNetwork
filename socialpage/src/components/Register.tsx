

import React from "react";



export function Register() {
    const submit = (e:React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const data = new FormData(e.target as HTMLFormElement);
        const registerForm:RegisterDto = {
            username: data.get('username') as string,
            nickname: data.get('nickname') as string,
            password: data.get('password') as string,
            description: data.get('description') as string,
            urlPerfil: data.get('urlPerfil') as string
        }
        console.log(registerForm);

    }
    return (
        <form className="register" onSubmit={submit}>
            <label htmlFor="register_username">Username</label>
            <input type="text" name="username" id="register_username" />

            <label htmlFor="register_password">Password</label>
            <input type="password" name="password" id="register_password" />

            <label htmlFor="register_password">Nickname</label>
            <input type="text" name="nickname" id="register_nickname" />

            <label htmlFor="register_description">Description</label>
            <input type="text" name="description" id="register_description" />

            <label htmlFor="register_urlPerfil">Url photo</label>
            <input type="text" name="urlPerfil" id="register_urlPerfil" />

            <button className="" type="submit">Entrar</button>
        </form>
    );
}
