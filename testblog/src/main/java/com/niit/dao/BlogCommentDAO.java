package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface BlogCommentDAO 
{
void addBlogComment(BlogComment blogComment);
List<BlogComment> getBlogComments(int blogPostId);
}
 