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
    async userInfo(token:string):Promise<UserInfo>{
        const ft = await fetch(`${propsApi.baseUrl}/user/userInfo`, {
            method:'GET',
            headers:{
                ...propsApi.onlyAuth(token)
            }
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
}