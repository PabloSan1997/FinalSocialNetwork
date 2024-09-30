/// <reference types="vite/client" />


type Children = {
    children:JSX.Element|JSX.Element[]
}

interface InitialStateAuthentication{
    username: string;
    message: string;
    token: string;
}

// interface IntitialStateSocial{

// }

interface ErroDto {
    error: string;
    message: string;
    statusCode: number;
    timestamp: string;
}

interface UserInfo {
    id: number,
    urlPerfil: string,
    description: string,
    user: Users
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
