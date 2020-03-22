import TaskAPI from "../api/task";

const CREATING_TASK = "CREATING_TASK",
    CREATING_TASK_SUCCESS = "CREATING_TASK_SUCCESS",
    CREATING_TASK_ERROR = "CREATING_TASK_ERROR",
    FETCHING_TASKS = "FETCHING_TASKS",
    FETCHING_TASKS_SUCCESS = "FETCHING_TASKS_SUCCESS",
    FETCHING_TASKS_ERROR = "FETCHING_TASKS_ERROR";

export default {
    namespaced: true,
    state: {
        isLoading: false,
        error: null,
        tasks: []
    },
    getters: {
        isLoading(state) {
            return state.isLoading;
        },
        hasError(state) {
            return state.error !== null;
        },
        error(state) {
            return state.error;
        },
        hasTasks(state) {
            return state.tasks.length > 0;
        },
        tasks(state) {
            return state.tasks;
        }
    },
    mutations: {
        [CREATING_TASK](state) {
            state.isLoading = true;
            state.error = null;
        },
        [CREATING_TASK_SUCCESS](state, task) {
            state.isLoading = false;
            state.error = null;
            state.tasks.push(task);
        },
        [CREATING_TASK_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.tasks = [];
        },
        [FETCHING_TASKS](state) {
            state.isLoading = true;
            state.error = null;
            state.tasks = [];
        },
        [FETCHING_TASKS_SUCCESS](state, tasks) {
            state.isLoading = false;
            state.error = null;
            state.tasks = [tasks];
        },
        [FETCHING_TASKS_ERROR](state, error) {
            state.isLoading = false;
            state.error = error;
            state.tasks = [];
        }
    },
    actions: {
        async create({ commit }, [ patientId, content, activeTo ]) {
            commit(CREATING_TASK);
            try {
                let response = await TaskAPI.create(patientId, content, activeTo);
                commit(CREATING_TASK_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(CREATING_TASK_ERROR, error);
                return null;
            }
        },
        async findAll({ commit }) {
            commit(FETCHING_TASKS);
            try {
                let response = await TaskAPI.findAll();
                commit(FETCHING_TASKS_SUCCESS, response.data);
                return response.data;
            } catch (error) {
                commit(FETCHING_TASKS_ERROR, error);
                return null;
            }
        },
    }
};
