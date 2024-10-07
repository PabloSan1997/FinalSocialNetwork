import { converDate } from "../utils/convertDate";


export  function ImagenShow(imageInfo:ShowImages) {
  return (
    <div className="image_show">
        <div className="image_user">
            <img src={imageInfo.urlPerfil} />
            <div className="area_user_info">
                <span>{imageInfo.nickname}</span>
                <span>@{imageInfo.username}</span>
                <span>{converDate(imageInfo.createAt)}</span>
            </div>
        </div>
        <p className="description">{imageInfo.description}</p>
        <img src={imageInfo.urlImage} alt={imageInfo.username} />
        <div className="image_info">
            <span>Comments: {imageInfo.comments}</span>
            <span>Likes: {imageInfo.likes}</span>
        </div>
    </div>
  );
}
