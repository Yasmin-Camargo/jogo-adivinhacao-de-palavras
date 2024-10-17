import axios, { AxiosPromise } from "axios"
import { WordData } from "../interface/WordData"
import { useQuery } from "@tanstack/react-query"

const API_URL = "http://localhost:8080"

const fetchData = async (): AxiosPromise<WordData[]> => {
    const response = axios.get(API_URL + "/words");
    return response;
}

export function useWordData() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['word-data'],
        retry: 2
    })

    return {
        ...query.data,
        data: query.data?.data
    }
}