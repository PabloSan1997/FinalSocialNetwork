

import { useNavigate } from "react-router-dom";

export default function UserFollow({username, nickname, userInfo}:UserImage) {
    const navigate = useNavigate();
    const go =()=>{
        navigate(`/perfilFriend?name=${username}`)
    }
  return (
    <div className="follow_user" onClick={go}>
      <img src={userInfo.urlPerfil} alt={username} />
      <div className="area_user_info">
        <span className="nickname">{nickname}</span>
        <span className="username">@{username}</span>
      </div>
    </div>
  );
}
