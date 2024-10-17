

import { useNavigate } from "react-router-dom";

export default function UserFollow({username, nickname, userInfo}:UserImage) {
    const navigate = useNavigate();
    const go =()=>{
        navigate(`/perfilFriend?name=${username}`)
    }
  return (
    <div className="follow_user">
      <img src={userInfo.urlPerfil} alt={username} onClick={go} />
      <div className="area_user_info">
        <span>{nickname}</span>
        <span onClick={go}>@{username}</span>
      </div>
    </div>
  );
}
