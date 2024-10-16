/* eslint-disable react-hooks/exhaustive-deps */


import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";

import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useSearchParams } from "react-router-dom";
import { ShowPerfil } from "../components/ShowPerfil";

export function PerfilFriend() {
    document.title = 'Perfil';
    const [searchParams] = useSearchParams();
    const username = searchParams.get('name');
    const dispatch = useAppDispatch();
    const socialState = useAppSelector(state => state.socialReducer);
    const { showUserInfo } = socialState;
    const authState = useAppSelector(state => state.authReducer);

    useEffect(() => {
        if (username) {
            dispatch(socialExtraReducer.getUserInfoFriend({ username, token: authState.token }));
            dispatch(socialExtraReducer.findFriendAllImages({ page: 0, username, token: authState.token }));
            document.title = 'Perfil|'+socialState.showUserInfo.username;
        }
    }, [username]);
    return (
        <ShowPerfil showUserInfo={showUserInfo} images={socialState.imagenes} />
    );
}
