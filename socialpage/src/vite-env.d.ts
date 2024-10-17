/// <reference types="vite/client" />


type Children = {
    children: JSX.Element | JSX.Element[]
}


interface InitialStateAuthentication {
    username: string;
    messageLogin: string;
    token: string;
    id: number;
    messageRegister: string;
}

interface InitialStateSocial {
    imagenes: ShowImages[],
    oneImage: ShowOneImage,
    showUserInfo: ShowUserInfo
    followUser: boolean;
    listFollow:FollowsResponseUser;
}

interface ErroDto {
    error: string;
    message: string;
    statusCode: number;
    timestamp: string;
}

interface ShowUserInfo {
    id: number;
    username: string;
    nickname: string;
    description: string;
    urlPerfil: string;
    following: number;
    followers: number;
}

interface ShowPerfil{
    showUserInfo:ShowUserInfo;
    images:ShowImages[];
    isMainPerfil:boolean;
}

interface Users {
    id: number,
    username: string,
    nickname: string,
    enabled: boolean,
    createAt: string,
    updateAt: string
}

interface LoginDto {
    username: string;
    password: string;
}
interface RegisterDto {
    username: string;
    nickname: string;
    password: string;
    description: string;
    urlPerfil: string;
}
interface LoginResponseDto {
    username: string;
    jwtoken: string;
}


interface ResponseFollowing {
    followingThisUser: boolean;
}

interface CountFollowing {
    following: number;
    followers: number;
}



interface FollowsResponseUser {
    users: {
        username: string;
        nickname: string;
        userInfo: UserInfo;
    }[]
}


//-------------images---------------

interface ShowImages {
    id: string;
    urlImage: string;
    description: string;
    createAt: string;
    username: string;
    nickname: string;
    urlPerfil: string;
    comments: number;
    likes: number;
    userLike: boolean;
}

interface ShowOneImage {
    id: string;
    urlImage: string;
    description: string;
    createAt: string;
    user: UserImage;
    comments: CommentInterface[];
    likes: number;
    userLike: boolean;
}

interface CommentInterface {
    id: number;
    createAt: stirng;
    comment: string;
    user: UserImage
}

interface UserImage {
    username: string;
    nickname: string;
    userInfo: UserInfo;
}

interface UserInfo {
    id: number;
    urlPerfil: string;
}

interface AddOneImage {
    urlImage: string;
    description: string;
}

interface LikeImageDto {
    idImage: string;
    countLikes: number;
    userLike: boolean;
}


interface AddComent {
    comment: string;
    idImage: string;
}

interface CommentResponse {
    id: number;
    createAt: string;
    comment: string;
    user: UserImage
}

interface ShowImageUser{
    userInfo:UserInfo,
    createAt:string;
    username:string;
    nickname:string;
}