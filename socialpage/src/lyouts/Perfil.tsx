/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { ShowPerfil } from "../components/ShowPerfil";




export function Perfil() {
  const dispatch = useAppDispatch();
  const socialState = useAppSelector(state => state.socialReducer);
  const { showUserInfo } = socialState;
  const authState = useAppSelector(state => state.authReducer);
  useEffect(() => {
      dispatch(socialExtraReducer.getMyOnwUser({ token: authState.token }));
      dispatch(socialExtraReducer.findFriendAllImages({ page: 0, username: authState.username, token: authState.token }));
      document.title = 'Perfil | ' + socialState.showUserInfo.username;
    
  }, [authState.username]);
  return (
    <ShowPerfil showUserInfo={showUserInfo} images={socialState.imagenes} />
  );
}
