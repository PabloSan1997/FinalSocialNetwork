import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { socialStorage } from "../utils/socialStorage";
import { authExtreReducer } from "./extraReducer/authExtreReducer";


const initialState:InitialStateAuthentication = {
    username: "",
    message: "",
    token: socialStorage.read()
}

const authSlice = createSlice({
    name:'slice/auth',
    initialState,
    reducers:{
        writeMessage(state, action:PayloadAction<{message:string}>){
            state.message = action.payload.message;
        }
    },
    extraReducers:builder =>{
        builder.addCase(authExtreReducer.login.fulfilled, (state, action)=>{
            state.token = action.payload.jwtoken;
            state.username = action.payload.username;
            state.message = '';
        });
        builder.addCase(authExtreReducer.login.rejected, (state, action)=>{
            const message = !action.error.message?'':action.error.message;
            state.message = message; 
        });

        builder.addCase(authExtreReducer.register.fulfilled, (state, action)=>{
            state.token = action.payload.jwtoken;
            state.username = action.payload.username;
            state.message='';
        });
        builder.addCase(authExtreReducer.register.rejected, (state, action)=>{
            const message = !action.error.message?'':action.error.message;
            state.message = message; 
        });
    }
});

export const authActions = authSlice.actions;
export const authReducer = authSlice.reducer;


