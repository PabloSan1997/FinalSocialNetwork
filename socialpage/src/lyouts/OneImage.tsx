/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useSearchParams } from "react-router-dom";
import { ImageUserPart } from "../components/ImageUserPart";
import { Comments } from "../components/Comments";
import CommentFrom from "../components/CommentFrom";
import { LikeSection } from "../components/LikeSection";
import { HomeNextComponent } from "../components/HomeNextComponent";
import '../styles/oneImage.scss';
import { XCircleIcon } from '@heroicons/react/24/solid'

export function OneImage() {
    const dispatch = useAppDispatch();
    const stateSocial = useAppSelector(state => state.socialReducer);
    const authState = useAppSelector(state => state.authReducer);
    const oneImage = stateSocial.oneImage;
    const comment = stateSocial.oneImage.comments;
    const userImageInfo = oneImage.user;
    const stateAuth = useAppSelector(state => state.authReducer);
    const [searchParams] = useSearchParams();
    const findText = searchParams.get('datos');
    const page = Number(searchParams.get('page'));
    const borrar = () =>{
        if(confirm('¿Seguro que desea borrar la imagen?'))
        dispatch(socialExtraReducer.deleteImage({token:authState.token, id:oneImage.id}));
    }
    useEffect(() => {
        if (findText)
            dispatch(socialExtraReducer.findOneImage({ token: stateAuth.token, idImage: findText, pageComment: page }));
    }, [findText, page]);
    return (
        <div className="area_one_image">
            <ImageUserPart {...userImageInfo} createAt={oneImage.createAt} />
            {authState.username === oneImage.user.username && <XCircleIcon onClick={borrar} className="close"/>}
            <p className="description">{oneImage.description}</p>
            <img src={oneImage.urlImage} alt={userImageInfo.username} className="image"/>
            <div className="image_info">
                <LikeSection idImage={oneImage.id} countLikes={oneImage.likes} userLike={oneImage.userLike}/>
            </div>
            <div className="area_comments">
                <h2 className="title_comment">Comentarios</h2>
                <CommentFrom idImage={oneImage.id}/>
                {comment.map(c => (
                    <Comments key={c.id} {...c}/>
                ))}
            </div>
            <HomeNextComponent pathbase={`/oneImage?datos=${findText}&page=`} page={page} className="next_comments"/>
        </div>
    );
}
