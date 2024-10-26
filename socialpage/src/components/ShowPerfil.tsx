import { Link } from 'react-router-dom';
import { FollowButton } from "./FollowButton";
import { FormAddImage } from "./FormAddImage";
import { ImagenShow } from "./ImagenShow";
import '../styles/perfil.scss';

export function ShowPerfil({ showUserInfo, images, isMainPerfil }: ShowPerfil) {

    return (
        <>
            <div className="perfil">
                <img src={showUserInfo.urlPerfil} alt={showUserInfo.username} className='perfil_imagen' />
                <div className="area_info_user">
                    <h2>{showUserInfo.nickname}</h2>
                    <h3>@{showUserInfo.username}</h3>
                    <FollowButton />
                    <p className="description">{showUserInfo.description}</p>
                    <nav>
                        <Link
                            to={`/followings?name=${showUserInfo.username}&page=0`}
                            className='follow'
                        >Following: {showUserInfo.following}</Link>
                        <Link
                            className='follow'
                            to={`/followers?name=${showUserInfo.username}&page=0`}
                        >Followers: {showUserInfo.followers}</Link>
                    </nav>
                </div>
            </div>
            {isMainPerfil && <FormAddImage />}
            <div className="container">
                {images.map(im => (
                    <ImagenShow key={im.id} {...im} />
                ))}
            </div>
        </>
    );
}
