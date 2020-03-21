FROM node:lts-alpine

WORKDIR /app
COPY . /app
RUN npm install

EXPOSE 8081
CMD [ "npm", "run", "serve" ]