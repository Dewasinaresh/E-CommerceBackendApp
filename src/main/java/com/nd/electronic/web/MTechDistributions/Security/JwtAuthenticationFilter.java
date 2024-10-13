package com.nd.electronic.web.MTechDistributions.Security;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.logging.Logger;

public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private Logger logger= (Logger) LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    // we have to perform JWT opetation in this class

    /***
     *  ******* WE HAVE TO PERFORM OPERATIONS ********
     *
     * A.get token from request
     * B.Validate token
     * C.get userName form token
     * D.set authentications
     *
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //Authorzation
        final String requestHeader = request.getHeader("Authorization");
        logger.info("Header: {}"+requestHeader);
        String userName=null;
        String token=null;
        if(requestHeader!=null && requestHeader.startsWith("Bearer"))
        {

            token=requestHeader.substring(7);
            try {

                userName = jwtHelper.getUsernameFromToken(token);

            }catch (IllegalArgumentException e)
            {
                logger.info("Illegal Argument Execption while fetching username !!!!!");
                e.printStackTrace();
            }
            catch (ExpiredJwtException e)
            {
                logger.info("JWT token has been expired !! ");
                e.printStackTrace();

            }
            catch (MalformedJwtException e){
                logger.info(" MalformedJwtException Something was change in Token ");
                e.printStackTrace();
            }


        }
        else {
            logger.info("Invalid token is provided");
        }
        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()!=null)
        {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            final Boolean validateTok = this.jwtHelper.validateToken(token, userDetails);
            if (validateTok)
            {
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                logger.info("fail to validate");
            }
        }
        filterChain.doFilter(request,response);








    }

}
