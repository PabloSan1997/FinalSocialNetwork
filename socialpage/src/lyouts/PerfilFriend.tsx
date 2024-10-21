/* eslint-disable react-hooks/exhaustive-deps */


import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";

import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useSearchParams } from "react-router-dom";
import { ShowPerfil } from "../components/ShowPerfil";
import { HomeNextComponent } from "../components/HomeNextComponent";

export function PerfilFriend() {
    const [searchParams] = useSearchParams();
    const username = searchParams.get('name');
    const page = Number(searchParams.get('page'));
    const dispatch = useAppDispatch();
    const socialState = useAppSelector(state => state.socialReducer);
    const { showUserInfo } = socialState;
    const authState = useAppSelector(state => state.authReducer);

    useEffect(() => {
        if (username && authState.username) {
            dispatch(socialExtraReducer.getUserInfoFriend({ username, token: authState.token })).then(() => {
                dispatch(socialExtraReducer.findFriendAllImages({ page: 0, username, token: authState.token }));
                dispatch(socialExtraReducer.isFollowingUser({ token: authState.token, username }));
                document.title = 'Person | ' + socialState.showUserInfo.username;
            });

        }
    }, [username, authState.username, page]);
    return (
        <>
            <ShowPerfil showUserInfo={showUserInfo} images={socialState.imagenes} isMainPerfil={false} />
            <HomeNextComponent
                pathbase={`/perfilFriend?name=${socialState.showUserInfo.username}&page=`}
                page={page}
                className="next_perfil"
            />
        </>
    );
}
