import axiosInstance from "../axios/axios.js";

const controller="authors";
const authorRepository={
    findAll:async ()=>{
        return await axiosInstance.get(`/${controller}`);
    },
    findWithMostRentedBooks:async ()=>{
        return await axiosInstance.get(`/${controller}/withMostRentedBooks`)
    },
    findByCountry:async ()=>{
        return await axiosInstance.get(`/${controller}/by-country`)
    },
    findNames:async ()=>{
        return await axiosInstance.get(`/${controller}/names`)
    }
}
export default authorRepository;