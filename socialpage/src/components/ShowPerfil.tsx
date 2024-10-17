import {Link} from 'react-router-dom';
import { FollowButton } from "./FollowButton";
import { FormAddImage } from "./FormAddImage";
import { ImagenShow } from "./ImagenShow";

export function ShowPerfil({showUserInfo, images, isMainPerfil}:ShowPerfil) {
    
    return (
        <>
            <div className="perfil">
                <img src={showUserInfo.urlPerfil} alt={showUserInfo.username}/>
                <h2>{showUserInfo.nickname}</h2>
                <h3>@{showUserInfo.username}</h3>
                <FollowButton/>
                <p className="description">{showUserInfo.description}</p>
                <Link to={`/followings?name=${showUserInfo.username}&page=0`}>Following: {showUserInfo.following}</Link>
                <Link to={`/followers?name=${showUserInfo.username}&page=0`}>Followers: {showUserInfo.followers}</Link>
            </div>
            {isMainPerfil && <FormAddImage/>}
            <div className="container">
                {images.map(im => (
                    <ImagenShow key={im.id} {...im} />
                ))}
            </div>
        </>
    );
}
