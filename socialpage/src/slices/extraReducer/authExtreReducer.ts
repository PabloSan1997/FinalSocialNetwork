import { createAsyncThunk } from "@reduxjs/toolkit";
import { UserApi } from "../../api/userApi"

const userApi = new UserApi();

export const authExtreReducer = {
    login:createAsyncThunk(
        'extra-reducer/login',
        async ({login}:{login:LoginDto}) =>{
            try {
                return userApi.login(login);
            } catch (error) {
                const arr = error as ErroDto;
                throw {message:arr.message}
            }
        }
    ),
    register:createAsyncThunk(
        'extra-reducer/register',
        async ({register}:{register:RegisterDto}) =>{
            try {
                return userApi.register(register);
            } catch (error) {
                const arr = error as ErroDto;
                throw {message:arr.message}
            }
        }
    )
}