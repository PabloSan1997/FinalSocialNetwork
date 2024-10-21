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


export function OneImage() {
    const dispatch = useAppDispatch();
    const stateSocial = useAppSelector(state => state.socialReducer);
    const oneImage = stateSocial.oneImage;
    const comment = stateSocial.oneImage.comments;
    const userImageInfo = oneImage.user;
    const stateAuth = useAppSelector(state => state.authReducer);
    const [searchParams] = useSearchParams();
    const findText = searchParams.get('datos');
    const page = Number(searchParams.get('page'));
    useEffect(() => {
        if (findText)
            dispatch(socialExtraReducer.findOneImage({ token: stateAuth.token, idImage: findText, pageComment: page }));
    }, [findText, page]);
    return (
        <div className="area_perfil">
            <ImageUserPart {...userImageInfo} createAt={oneImage.createAt} />
            <p>{oneImage.description}</p>
            <img src={oneImage.urlImage} alt={userImageInfo.username} />
            <div className="image_info">
                <LikeSection idImage={oneImage.id} countLikes={oneImage.likes} userLike={oneImage.userLike}/>
            </div>
            <div className="area_comments">
                <CommentFrom idImage={oneImage.id}/>
                <h2>Comentarios</h2>
                {comment.map(c => (
                    <Comments key={c.id} {...c}/>
                ))}
            </div>
            <HomeNextComponent pathbase={`/oneImage?datos=${findText}&page=`} page={page} className="next_comments"/>
        </div>
    );
}
