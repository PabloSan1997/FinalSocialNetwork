/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { authExtreReducer } from "../slices/extraReducer/authExtreReducer";


export function HeaderMenu() {
    const stateAuth = useAppSelector(state => state.authReducer);
    const dispatch = useAppDispatch();

    useEffect(() => {
        if (stateAuth.token.trim())
            dispatch(authExtreReducer.userInfo({ token: stateAuth.token }));
    }, [stateAuth.token]);
    if (!stateAuth.token.trim())
        return <></>;
    return (
        <nav className='header_menu'>
            <Link to={'/perfil'}>{stateAuth.username}</Link>
            <button
                onClick={() => dispatch(authExtreReducer.logout({ token: stateAuth.token }))}
            >Logout</button>
        </nav>
    );
}
