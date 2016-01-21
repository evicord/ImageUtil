//
//  ImageUtil.m
//  panart
//
//  Created by zsly on 16/1/13.
//
//

#import "ImageUtil.h"

@interface ImageUtil()
@property (nonatomic,strong) NSString* callbackId;
@end


@implementation ImageUtil
-(void)toBinary:(CDVInvokedUrlCommand*)command
{
    self.callbackId=[NSString stringWithString:command.callbackId];
    NSString *path=command.arguments[0];
    NSThread *thread=[[NSThread alloc] initWithTarget:self selector:@selector(handleData:) object:path];
    [thread start];
}

-(void)handleData:(NSString*)path
{
    NSError *error=nil;
    NSData *data=[NSData dataWithContentsOfFile:path];// options:NSDataReadingMappedIfSafe error:&error];
    CDVPluginResult*pluginResult=nil;
    if(error)
    {
        pluginResult= [CDVPluginResult resultWithStatus:CDVCommandStatus_IO_EXCEPTION messageAsString:error.localizedDescription];
    }
    else
    {
        pluginResult= [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArrayBuffer:data];
    }
    [self performSelectorOnMainThread:@selector(SendMsg:) withObject:pluginResult waitUntilDone:YES];
}

-(void)SendMsg:(CDVPluginResult*)pluginResult
{
    [pluginResult setKeepCallbackAsBool:YES];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:self.callbackId];
}

@end
