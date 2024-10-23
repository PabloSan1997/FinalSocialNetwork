import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { HeartIcon } from '@heroicons/react/24/solid';


export  function LikeSection({userLike, countLikes, idImage}:LikeImageDto) {
    const dispatch = useAppDispatch();
    const auttState = useAppSelector(state => state.authReducer);
    const generatelike=()=>{
        dispatch(socialExtraReducer.generateLike({token:auttState.token, id:idImage}))
    }
  return (
    <span onClick={generatelike}><HeartIcon className={!userLike?'icono':'icono active'}/> {countLikes}</span>
  );
}
