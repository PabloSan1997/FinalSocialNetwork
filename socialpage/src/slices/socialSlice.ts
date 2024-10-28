
import { createSlice } from "@reduxjs/toolkit";
import { socialExtraReducer } from "./extraReducer/socialExtraReducer";

const initialState: InitialStateSocial = {
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
    followUser: false,
    listFollow: { users: [] }
}

const socialSlice = createSlice({
    name: 'slice/social',
    initialState,
    reducers: {
        resetFollowUser(state) {
            state.followUser = false;
        },
        resetUserInfo(state) {
            state.showUserInfo = initialState.showUserInfo;
        },
        resetImagen(state) {
            state.oneImage = initialState.oneImage;
        }
    },
    extraReducers: builder => {
        //Images and userinfo
        builder.addCase(socialExtraReducer.findAllImages.fulfilled, (state, action) => {
            state.imagenes = action.payload;
        });
        builder.addCase(socialExtraReducer.findAllImages.rejected, (state) => {
            state.imagenes = [];
        });
        builder.addCase(socialExtraReducer.saveImage.fulfilled, (state, aciton) => {
            const images = state.imagenes;
            state.imagenes = [aciton.payload, ...images];
        });
        builder.addCase(socialExtraReducer.deleteImage.fulfilled, (state, action)=>{
            const images = state.imagenes.filter(p => p.id !== action.payload.id);
            state.imagenes = images;
        });
        builder.addCase(socialExtraReducer.findOneImage.fulfilled, (state, action) => {
            state.oneImage = action.payload;
        });

        builder.addCase(socialExtraReducer.getMyOnwUser.fulfilled, (state, action) => {
            state.showUserInfo = action.payload;
        });
        builder.addCase(socialExtraReducer.getUserInfoFriend.fulfilled, (state, action) => {
            state.showUserInfo = action.payload;
        });
        builder.addCase(socialExtraReducer.findFriendAllImages.fulfilled, (state, aciton) => {
            state.imagenes = aciton.payload;
        });

        //Comments
        builder.addCase(socialExtraReducer.commentImage.fulfilled, (state, action) => {
            const comments = state.oneImage.comments;
            state.oneImage.comments = [action.payload, ...comments];
        });
        builder.addCase(socialExtraReducer.deleteComment.fulfilled, (state, action)=>{
            state.oneImage.comments = state.oneImage.comments.filter(c => c.id !== action.payload.id);
        });

        //Likes
        builder.addCase(socialExtraReducer.generateLike.fulfilled, (state, action) => {
            const res = action.payload
            const index = state.imagenes.findIndex(im => im.id == res.idImage);
            state.imagenes[index].likes = res.countLikes;
            state.imagenes[index].userLike = res.userLike;

            if (state.oneImage.id == res.idImage) {
                state.oneImage.likes = res.countLikes;
                state.oneImage.userLike = res.userLike;
            }
        });

        //follow funcitons
        builder.addCase(socialExtraReducer.isFollowingUser.fulfilled, (state, action) => {
            state.followUser = action.payload.followingThisUser;
        });
        builder.addCase(socialExtraReducer.generateFollow.fulfilled, (state, action) => {
            state.followUser = action.payload.followingThisUser;
        });
        builder.addCase(socialExtraReducer.countFollowing.fulfilled, (state, action) => {
            state.showUserInfo.followers = action.payload.followers;
            state.showUserInfo.following = action.payload.following;
        });
        builder.addCase(socialExtraReducer.getFollowers.fulfilled, (state, action)=>{
            state.listFollow = action.payload;
        });
        builder.addCase(socialExtraReducer.getFollowings.fulfilled, (state, action)=>{
            state.listFollow = action.payload;
        });

        //Search users
        builder.addCase(socialExtraReducer.searchUsers.fulfilled, ()=>{});
    }
});


export const socialReducer = socialSlice.reducer;
export const socialAction = socialSlice.actions;