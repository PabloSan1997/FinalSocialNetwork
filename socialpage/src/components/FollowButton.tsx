/* eslint-disable react-hooks/exhaustive-deps */

import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";

export function FollowButton() {
    const dispatch = useAppDispatch();
    const authState = useAppSelector(state => state.authReducer);
    const socialState = useAppSelector(state => state.socialReducer);
    const generateLike = () => {
        dispatch(socialExtraReducer.generateFollow({ id: socialState.showUserInfo.id, token: authState.token }))
            .then(() =>
                dispatch(socialExtraReducer.countFollowing({ id: socialState.showUserInfo.id, token: authState.token }))
            );
    }

    if (authState.username === socialState.showUserInfo.username)
        return null;
    return (
        <button className={socialState.followUser?'follow_button active':'follow_button'} onClick={generateLike}>{socialState.followUser ? 'Siguiendo' : 'Seguir'}</button>
    );
}
