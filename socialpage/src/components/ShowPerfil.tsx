
import { ImagenShow } from "./ImagenShow";

export function ShowPerfil({showUserInfo, images}:ShowPerfil) {

    return (
        <>
            <div className="perfil">
                <img src={showUserInfo.urlPerfil} alt={showUserInfo.username}/>
                <h2>{showUserInfo.nickname}</h2>
                <h3>@{showUserInfo.username}</h3>
                <p className="description">{showUserInfo.description}</p>
                <span>Followers: {showUserInfo.followers}</span>
                <span>Following: {showUserInfo.following}</span>
            </div>
            <div className="container">
                {images.map(im => (
                    <ImagenShow key={im.id} {...im} />
                ))}
            </div>
        </>
    );
}
