import {useCallback, useEffect, useState} from "react";
import bookRepository from "../repository/bookRepository.js";


const initialState = {
    "books": [],
    "loading": true,
};


const useBooks = () => {
    const [state, setState] = useState(initialState);

    const fetchBooks = useCallback(() => {
        bookRepository.findAll()
            .then((response) => {
                setState({
                    "books": response.data,
                    "loading": false
                });
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);

    const onAdd = useCallback((data) => {
        bookRepository.add(data)
            .then((response) => {
                console.log(response);
                fetchBooks();
            })
            .catch((err) => {
                console.log(err);
            });
    }, [fetchBooks]);

    const onEdit = useCallback((id, data) => {
        bookRepository.edit(id, data).then((response) => {
            console.log(response);
            fetchBooks();
        })
            .catch((err) => {
                console.log(err);
            });
    }, [fetchBooks]);

    const onDelete = useCallback((id) => {
        bookRepository.delete(id).then((response) => {
            console.log(response);
            fetchBooks();
        })
            .catch((err) => {
                console.log(err);
            });
    }, [fetchBooks]);

    useEffect(() => {
        fetchBooks();
    }, [fetchBooks]);
    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
};

export default useBooks;