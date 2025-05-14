import axiosInstance from "../axios/axios.js";

const controller="books";

const bookRepository={
    findAll:async ()=>{
        return await axiosInstance.get(`/${controller}`);
    },
    findMostRented:async ()=>{
        return await axiosInstance.get(`/${controller}/mostRented`);
    },
    findByAuthor:async ()=>{
        return await axiosInstance.get(`/${controller}/by-author`)
    },
    findById:async (id)=>{
        return await axiosInstance.get(`/${controller}/${id}`);
    },
    delete:async (id)=>{
        return await axiosInstance.delete(`/${controller}/${id}/delete`)
    },
    edit:async (id,data)=>{
        return await axiosInstance.put(`/${controller}/${id}/edit`,data)
    },
    add:async (data)=>{
        return await axiosInstance.post(`/${controller}/add`,data)
    }
}
export default bookRepository;