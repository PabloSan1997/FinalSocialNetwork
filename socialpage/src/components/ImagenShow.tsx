import { useNavigate } from 'react-router-dom';
import { ImageUserPart } from "./ImageUserPart";
import { LikeSection } from './LikeSection';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { socialExtraReducer } from '../slices/extraReducer/socialExtraReducer';
import { ChatBubbleLeftIcon, XCircleIcon } from '@heroicons/react/24/solid';

export function ImagenShow(imageInfo: ShowImages) {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const authState = useAppSelector(state => state.authReducer);
  const borrar = () => {
    if (confirm('¿Seguro que desea borrar la imagen?'))
      dispatch(socialExtraReducer.deleteImage({ token: authState.token, id: imageInfo.id }));
  }
  return (
    <div className="image_show">
      <ImageUserPart
        username={imageInfo.username}
        nickname={imageInfo.nickname}
        userInfo={{ urlPerfil: imageInfo.urlPerfil, id: 0 }}
        createAt={imageInfo.createAt}
      />
      {authState.username == imageInfo.username && <XCircleIcon  className='close' onClick={borrar}/>}
      <p className="description">{imageInfo.description}</p>
      <div className="area_image">
        <img
          src={imageInfo.urlImage}
          alt={imageInfo.username}
          onClick={() => navigate(`/oneImage?datos=${imageInfo.id}`)}
          className='imagen'
        />
      </div>
      <div className="image_info">
        <span><ChatBubbleLeftIcon className='icono'/> {imageInfo.comments}</span>
        <LikeSection idImage={imageInfo.id} userLike={imageInfo.userLike} countLikes={imageInfo.likes} />
      </div>
    </div>
  );
}
