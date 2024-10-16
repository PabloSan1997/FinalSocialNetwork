import { ImageUserPart } from "./ImageUserPart";



export function Comments({user, comment, createAt}:CommentInterface) {
  return (
    <div className="comment">
        <ImageUserPart {...user} createAt={createAt}/>
        <p>{comment}</p>
    </div>
  );
}
