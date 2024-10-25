import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { ImageUserPart } from "./ImageUserPart";
import { XCircleIcon } from '@heroicons/react/24/solid';


export function Comments({user, comment, createAt, id}:CommentInterface) {
  const dispatch = useAppDispatch();
  const authState = useAppSelector(state => state.authReducer);
  const borrar = () => {
    if(confirm('¿Seguro que desea borrar comentario'))
    dispatch(socialExtraReducer.deleteComment({token:authState.token, idComment:id}));
  }
  return (
    <div className="comment">
        <ImageUserPart {...user} createAt={createAt}/>
        {authState.username == user.username && <XCircleIcon onClick={borrar} className="icono"/>}
        <p>{comment}</p>
    </div>
  );
}
