import { createAsyncThunk } from "@reduxjs/toolkit";
import { ImageApi } from "../../api/imageApi";
import { CommentApi } from "../../api/commentApi";
import { UserApi } from "../../api/userApi";

const imageApi = new ImageApi();
const commentApi = new CommentApi();
const userApi = new UserApi();

export const socialExtraReducer = {
    findAllImages: createAsyncThunk(
        'extraReducer/findAllImages',
        async ({ page, token }: { page: number, token: string }) => {
            try {
                return imageApi.readImages(token, null, page);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    findFriendAllImages: createAsyncThunk(
        'extraReducer/findAllFriendImages',
        async ({ page, token, username }: { page: number, token: string, username: string }) => {
            try {
                return imageApi.readImages(token, username, page);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    findOneImage: createAsyncThunk(
        'extraReducer/findOneImage',
        async ({ token, idImage, pageComment }: { token: string, idImage: string, pageComment: number }) => {
            try {
                return imageApi.readOneImage(token, idImage, pageComment);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    saveImage: createAsyncThunk(
        'extraReducer/saveImage',
        async ({ newImage, token }: { newImage: AddOneImage, token: string }) => {
            try {
                return imageApi.postImages(token, newImage);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    deleteImage: createAsyncThunk(
        'extraReducer/deleteImage',
        async ({ id, token }: { id: string, token: string }) => {
            try {
                return imageApi.deleteImage(token, id)
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    generateLike: createAsyncThunk(
        'extraReducer/generateLike',
        async ({ token, id }: { token: string, id: string }) => {
            try {
                return imageApi.addLikeImage(token, id);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    commentImage: createAsyncThunk(
        'extraReducer/commentImage',
        async ({ token, addComment }: { token: string, addComment: AddComent }) => {
            try {
                return commentApi.saveComment(token, addComment);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    deleteComment: createAsyncThunk(
        'extraReducer/deleteComment',
        async ({ token, idComment }: { token: string, idComment: number }) => {
            try {
                return commentApi.deleteComment(token, idComment);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    getUserInfoFriend: createAsyncThunk(
        'extraReducer/userInfoFriend',
        async ({ token, username }: { token: string, username: string }) => {
            try {
                return userApi.getUserInfo(token, username);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    getMyOnwUser: createAsyncThunk(
            'extraReducer/MyUserInfo',
            async ({ token }: { token: string}) => {
                try {
                    return userApi.getUserInfo(token);
                } catch (error) {
                    const err = error as ErroDto;
                    throw { meesage: err.message };
                }
            }
    ),
    isFollowingUser: createAsyncThunk(
        'extraReducer/isFollowingUser',
        async ({ token, username }: { token: string, username: string }) => {
            try {
                return userApi.userFollow(token, username);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    getFollowers: createAsyncThunk(
        'extraReducer/getFollowers',
        async ({ token, page, id }: { token: string, page: number, id: number }) => {
            try {
                return userApi.getFollowers(token, id, page)
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    getFollowings: createAsyncThunk(
        'extraReducer/getFollowings',
        async ({ token, page, id }: { token: string, page: number, id: number }) => {
            try {
                return userApi.getFollowings(token, id, page)
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    countFollowing: createAsyncThunk(
        'extraReducer/countFollowing',
        async ({ token, id }: { token: string, id: number }) => {
            try {
                return userApi.countFollowing(token, id);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    ),
    generateFollow:createAsyncThunk(
        'extraReducer/generateFollowing',
        async ({ token, id }: { token: string, id: number }) => {
            try {
                return userApi.addFollowing(token, id);
            } catch (error) {
                const err = error as ErroDto;
                throw { meesage: err.message };
            }
        }
    )
}