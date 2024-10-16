import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useAppDispatch, useAppSelector } from "../store/hooks";



export  function LikeSection({userLike, countLikes, idImage}:LikeImageDto) {
    const dispatch = useAppDispatch();
    const auttState = useAppSelector(state => state.authReducer);
    const generatelike=()=>{
        dispatch(socialExtraReducer.generateLike({token:auttState.token, id:idImage}))
    }
  return (
    <span onClick={generatelike}>likes: {countLikes} {userLike && 'likeado'}</span>
  );
}
