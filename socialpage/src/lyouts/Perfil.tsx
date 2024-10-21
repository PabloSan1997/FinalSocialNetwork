/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { ShowPerfil } from "../components/ShowPerfil";
import { useSearchParams } from "react-router-dom";
import { HomeNextComponent } from "../components/HomeNextComponent";




export function Perfil() {
  const dispatch = useAppDispatch();
  const socialState = useAppSelector(state => state.socialReducer);
  const [searchParams] = useSearchParams();
  const page = Number(searchParams.get('page'));
  const { showUserInfo } = socialState;
  const authState = useAppSelector(state => state.authReducer);

  useEffect(() => {
    dispatch(socialExtraReducer.getMyOnwUser({ token: authState.token }));
    dispatch(socialExtraReducer.findFriendAllImages({ page, username: authState.username, token: authState.token }));
    document.title = 'Perfil | ' + authState.username;

  }, [authState.username, page]);

  return (
    <>
      <ShowPerfil showUserInfo={showUserInfo} images={socialState.imagenes} isMainPerfil={true} />
      <HomeNextComponent pathbase="/perfil?page=" page={page} className="next_perfil"/>
    </>
  );
}
