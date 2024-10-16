import { useNavigate } from "react-router-dom";
import { converDate } from "../utils/convertDate";
import { useAppSelector } from "../store/hooks";




export function ImageUserPart({userInfo, createAt, username, nickname}:ShowImageUser) {
  const navigate = useNavigate();
  const authState = useAppSelector(state=>state.authReducer);
  const go = ()=>{
    if(username===authState.username)
      navigate('/perfil');
    else
      navigate(`/perfilFriend?name=${username}`);
  }
  return (
    <div className="image_user">
            <img src={userInfo.urlPerfil} alt={username} onClick={go}/>
            <div className="area_user_info">
                <span>{nickname}</span>
                <span onClick={go}>@{username}</span>
                <span>{converDate(createAt)}</span>
            </div>
        </div>
  );
}
