#!/bin/bash
# Renme

echo What is the old post name? e.g. 2021-04-20-dog-robot-woman
read old_post

echo What is the new post name? e.g. 2021-04-22-identity-through-time
read new_post

printf "\n"

mv content/posts/$old_post.md content/posts/$new_post.md
mv resources/public/img/$old_post resources/public/img/$new_post

ls content/posts/$new_post.md
ls resources/public/img/$new_post

printf "\n"

cd content/posts

echo 'New post name:'$new_post
echo 'Old post name:' $old_post
