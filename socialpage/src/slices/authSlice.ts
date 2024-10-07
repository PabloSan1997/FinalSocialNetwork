import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { socialStorage } from "../utils/socialStorage";
import { authExtreReducer } from "./extraReducer/authExtreReducer";


const initialState:InitialStateAuthentication = {
    username: "",
    messageLogin: "",
    messageRegister:"",
    token: socialStorage.read(),
    id:0
}

const authSlice = createSlice({
    name:'slice/auth',
    initialState,
    reducers:{
        writeMessageLogin(state, action:PayloadAction<{message:string}>){
            state.messageLogin = action.payload.message;
        },
        writeMessageRegister(state, action:PayloadAction<{message:string}>){
            state.messageRegister = action.payload.message;
        }
    },
    extraReducers:builder =>{
        builder.addCase(authExtreReducer.login.fulfilled, (state, action)=>{
            state.token = action.payload.jwtoken;
            state.username = action.payload.username;
            state.messageLogin = '';
            state.messageRegister = '';
            socialStorage.save(action.payload.jwtoken);
        });
        builder.addCase(authExtreReducer.login.rejected, (state, action)=>{
            const message = !action.error.message?'':action.error.message;
            state.messageLogin = message; 
        });

        builder.addCase(authExtreReducer.register.fulfilled, (state, action)=>{
            state.token = action.payload.jwtoken;
            state.username = action.payload.username;
            state.messageLogin = '';
            state.messageRegister = '';
            socialStorage.save(action.payload.jwtoken);
        });
        builder.addCase(authExtreReducer.register.rejected, (state, action)=>{
            const message = !action.error.message?'':action.error.message;
            state.messageRegister = message; 
        });

        builder.addCase(authExtreReducer.logout.fulfilled, (state)=>{
            state.token = '';
            state.messageLogin = initialState.messageLogin;
            state.messageRegister = initialState.messageRegister
            state.username = initialState.username;
            state.id = 0;
            socialStorage.save('');
        });

        builder.addCase(authExtreReducer.userInfo.fulfilled, (state, action)=>{
            state.username = action.payload.username;
            state.messageLogin = '';
            state.messageRegister = '';
            state.id = action.payload.id;
        });
        builder.addCase(authExtreReducer.userInfo.rejected, (state)=>{
            state.token = '';
            socialStorage.save('');
            state.username = '';
        });
    }
});

export const authActions = authSlice.actions;
export const authReducer = authSlice.reducer;


