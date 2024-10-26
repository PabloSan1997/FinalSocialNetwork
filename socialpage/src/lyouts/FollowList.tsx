/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { Navigate, useSearchParams } from "react-router-dom";
import UserFollow from "../components/UserFollow";
import '../styles/followList.scss';


export function FollowList({ isFollowing }: { isFollowing: boolean }) {
  const dispatch = useAppDispatch();
  const [params] = useSearchParams();

  const socialState = useAppSelector(state => state.socialReducer);
  const authState = useAppSelector(state => state.authReducer);
  const token = authState.token;
  const page: number = isNaN(Number(params.get('page'))) ? 0 : Number(params.get('page'));
  const username: string = params.get('name') as string;
  useEffect(() => {
    if (!isNaN(page))
      if (isFollowing)
        dispatch(socialExtraReducer.getFollowings({ token, username: username, page }));
      else
        dispatch(socialExtraReducer.getFollowers({ token, username: username, page }));
  }, [isFollowing]);

  if (!username || !username.trim())
    return <Navigate to={'/home'} />
  return (
    <div className="list_follow">
      <h2>{isFollowing?'Followings: ':'Followers: '} {username}</h2>
      {socialState.listFollow.users.map(f => (
        <UserFollow key={f.username} {...f} />
      ))}
    </div>
  );
}
