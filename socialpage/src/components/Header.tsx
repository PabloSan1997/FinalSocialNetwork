/* eslint-disable react-hooks/exhaustive-deps */
import { Link } from "react-router-dom";
import { HeaderMenu } from "./HeaderMenu";
import '../styles/header.scss';

export function Header() {
   
    
    return (
        <header>
            <h1>
                <Link to={'/home'}>Mi Perfil</Link>
            </h1>
            <HeaderMenu/>
        </header>
    )
}