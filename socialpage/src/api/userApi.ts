import { propsApi } from "./propsApi";

export class UserApi {
    async login(data: LoginDto): Promise<LoginResponseDto> {
        const request: RequestInit = {
            method: 'POST',
            headers: {
                ...propsApi.onlyJson()
            },
            body: JSON.stringify(data)
        }
        const ft = await fetch(`${propsApi.baseUrl}/user/login`, request);
        if (!ft.ok) {
            throw await ft.json() as ErroDto;
        }
        return ft.json();
    }
    async register(data: RegisterDto): Promise<LoginResponseDto> {
        const request: RequestInit = {
            method: 'POST',
            headers: {
                ...propsApi.onlyJson()
            },
            body: JSON.stringify(data)
        }
        const ft = await fetch(`${propsApi.baseUrl}/user/register`, request);
        if (!ft.ok) {
            throw await ft.json() as ErroDto;
        }
        return ft.json();
    }
    async logout(token: string): Promise<void> {
        const ft = await fetch(`${propsApi.baseUrl}/user/logout`, {
            method: 'POST',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
    }
    async userInfo(token: string): Promise<ShowUserInfo> {
        const ft = await fetch(`${propsApi.baseUrl}/user/userInfo`, {
            method: 'GET',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async userFollow(token: string, username: string): Promise<ResponseFollowing> {
        const ft = await fetch(`${propsApi.baseUrl}/user/follow/followUser?username=${username}`, {
            method: 'GET',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async addFollowing(token: string, id: number): Promise<ResponseFollowing> {
        const ft = await fetch(`${propsApi.baseUrl}/user/follow/${id}`, {
            method: 'POST',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async countFollowing(token: string, id: number): Promise<CountFollowing> {
        const ft = await fetch(`${propsApi.baseUrl}/user/follow/count/${id}`, {
            method: 'GET',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async getFollowers(token: string, id: number, page = 0, size = 10): Promise<FollowsResponseUser> {
        const ft = await fetch(`${propsApi.baseUrl}/user/follow/find/followers/${id}?page=${page}&size=${size}`, {
            method: 'GET',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async getFollowings(token: string, id: number, page = 0, size = 10): Promise<FollowsResponseUser> {
        const ft = await fetch(`${propsApi.baseUrl}/user/follow/find/followings/${id}}?page=${page}&size=${size}`, {
            method: 'GET',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async getUserInfo(token:string, username:string|null):Promise<ShowUserInfo>{
        const baseUrl = !username?'user/userInfo':`user/otherUser?username=${username}`;
        const ft = await fetch(baseUrl, {
            method: 'GET',
            headers: {
                ...propsApi.onlyAuth(token)
            }
        });
        if (!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
}