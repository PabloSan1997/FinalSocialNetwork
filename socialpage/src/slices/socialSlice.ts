
import { createSlice } from "@reduxjs/toolkit";
import { socialExtraReducer } from "./extraReducer/socialExtraReducer";
import { authExtreReducer } from "./extraReducer/authExtreReducer";

const initialState:InitialStateSocial = {
    imagenes: [],
    oneImage: {
        id: "",
        urlImage: "",
        description: "",
        createAt: "",
        user: {
            username: "",
            nickname: "",
            userInfo: {
                id: 0,
                urlPerfil: ""
            }
        },
        comments: [],
        likes: 0,
        userLike: false
    },
    showUserInfo: {
        id: 0,
        username: "",
        nickname: "",
        description: "",
        urlPerfil: "",
        following: 0,
        followers: 0
    },
    followUser: false
}

const socialSlice = createSlice({
    name:'slice/social',
    initialState,
    reducers:{
        resetFollowUser(state){
            state.followUser = false;
        },
        resetUserInfo(state){
            state.showUserInfo = initialState.showUserInfo;
        },
        resetImagen(state){
            state.oneImage = initialState.oneImage;
        }
    },
    extraReducers:builder => {
        builder.addCase(socialExtraReducer.findAllImages.fulfilled, (state, action)=>{
            state.imagenes = action.payload;
        });
        builder.addCase(socialExtraReducer.findAllImages.rejected, (state)=>{
            state.imagenes = [];
        });

        builder.addCase(socialExtraReducer.findOneImage.fulfilled, (state, action)=>{
            state.oneImage = action.payload;
        });

        builder.addCase(authExtreReducer.userInfo.fulfilled, (state, action)=>{
            state.showUserInfo = action.payload;
        });
        builder.addCase(socialExtraReducer.getUserInfoFriend.fulfilled, (state, action)=>{
            state.showUserInfo = action.payload;
        });
        builder.addCase(socialExtraReducer.findFriendAllImages.fulfilled, (state, aciton)=>{
            state.imagenes = aciton.payload;
        });
    }
});


export const socialReducer = socialSlice.reducer;
export const socialAction = socialSlice.actions;