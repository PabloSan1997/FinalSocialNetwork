import { useNavigate } from 'react-router-dom';
import { ImageUserPart } from "./ImageUserPart";

export function ImagenShow(imageInfo: ShowImages) {
  const navigate = useNavigate();
  return (
    <div className="image_show">
      <ImageUserPart
        username={imageInfo.username}
        nickname={imageInfo.nickname}
        userInfo={{ urlPerfil: imageInfo.urlPerfil, id: 0 }}
        createAt={imageInfo.createAt}
      />
      <p className="description">{imageInfo.description}</p>
      <img src={imageInfo.urlImage} alt={imageInfo.username} onClick={() => navigate(`/oneImage?datos=${imageInfo.id}`)} />
      <div className="image_info">
        <span>Comments: {imageInfo.comments}</span>
        <span>Likes: {imageInfo.likes}</span>
      </div>
    </div>
  );
}
