FROM node

WORKDIR /app

COPY . .
RUN rm -rf node_modules
RUN npm i

# EXPOSE [Port you mentioned in the vite.config file]

EXPOSE 8082

CMD ["npm", "run", "dev"]